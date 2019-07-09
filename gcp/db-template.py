def generateDependencies(deps):
	if len(deps) > 0:
		return {
			'dependsOn': deps
		}
	else:
		return {}


def EnableAPIs(name, project_id):
	return {
		'name': name,
		'type': 'deploymentmanager.v2.virtual.enableService',
		'properties': {
			'consumerId': 'project:{}'.format(project_id),
			'serviceName': 'sqladmin.googleapis.com'
		}
	}

def CreateCloudSQLInstance(basename, api_task, zone, maxSalt=None):
	import random
	random.seed()
	maxRand = 100 if maxSalt is None else maxSalt
	salt = random.randint(0, 100)
	instance_name = (basename + '-{}').format(salt)
	return (instance_name, {
		'name': instance_name,
		'type': 'sqladmin.v1beta4.instance',
		'metadata': generateDependencies([api_task]),
		'properties': {
			'backendType': 'SECOND_GEN',
			'databaseVersion': 'POSTGRES_11',
			'settings': {
				'availabilityType': 'REGIONAL',
				'backupConfiguration': {
					'enabled': True,
					'startTime': '23:00'
				},
				'dataDiskSizeGb': 10,
				'dataDiskType': 'PD_SSD',
				'locationPreference': {
					'zone': zone
				},
				'maintenanceWindow': {
					'day': 1,
					'hour': 23
				},
				'storageAutoResize': True,
				'tier': 'db-custom-1-3840'
			}
		}
	})

def CreateDatabaseInInstance(instance_name, db_name):
	return {
		'name': 'database-creation',
		'type': 'sqladmin.v1beta4.database',
		'metadata': generateDependencies([instance_name]),
		'properties': {
			'instance': instance_name,
			'name': db_name
		}
	}

def GenerateConfig(context):
	enable_api_resource_name = 'enable-api'
	project_id = context.env['project']
	max_db_idx = context.properties['maxDbIndex']
	instance_name, instance_resource = CreateCloudSQLInstance(
		'gcpmamie-prod', 
		enable_api_resource_name, 
		context.properties['zone'], 
		max_db_idx
	)
	return {
		'resources': [
			EnableAPIs(enable_api_resource_name, project_id),
			instance_resource,
			CreateDatabaseInInstance(instance_name, context.properties['dbName'])
		]
	}
