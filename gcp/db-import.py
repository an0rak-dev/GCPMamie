def getStorageName(context): 
	return context.env['project'] + '-' + context.properties['db-instance-name'] + '-dumps'

def generateSqlBucket(context):
	return {
		'type': 'storage.v1.bucket',
		'name': getStorageName(context),
		'projectNumber': context.env['project_number'],
		'location': 'europe-west1',
		'storageClass': 'REGIONAL',
		'iamConfiguration': {
			'bucketPolicyOnly': {
				'enabled': False
			}
		}
	}


def authorizeDbToBucket(context):
	dbServiceAccount = context.properties['db-service-account']
	return {
		#'type': 'storage-v1:objectAccessControls.insert',
		'type': 'storage.v1.objectAccessControl',
		'name': 'authorize-db-to-read-bucket',
		'properties': {
			'bucket': context.properties['dump-bucket'],
			'object': context.properties['dump-object'],
			'entity': 'user-' + dbServiceAccount,
			'role': 'READER'
		},
		'metadata': {
			'dependsOn': [context.properties['db-instance-name'] + '-user']
		}
	}

def importDump(context):
	return {
		'action': 'gcp-types/sqladmin-v1beta4:sql.instances.import',
    	'name': 'import-datas',
    	'properties': {
      	'instance': context.properties['db-instance-name'],
     		'project': context.env['project'],
     		'importContext': {
        		'fileType': 'SQL',
				'database': 'postgres',
       		'uri': 'gs://' + context.properties['dump-bucket'] + '/' + context.properties['dump-object']
		  	}
		 },
    	'metadata': {
     		'dependsOn': ['authorize-db-to-read-bucket']
		 }
	}

def GenerateConfig(context):
	resources = [
#		generateSqlBucket(context),
		authorizeDbToBucket(context),
		importDump(context)
	]
	return { 'resources': resources }
