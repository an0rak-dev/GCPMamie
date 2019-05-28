
DB_NAME_KEY='db-instance-name'

def generateDbResource(context):
	return {
		'type': 'sqladmin.v1beta4.instance',
    	'name': context.properties[DB_NAME_KEY],
    	'properties': {
      	'gceZone': 'europe-west1-b',
			'region': 'europe-west1',
			'instanceType': 'CLOUD_SQL_INSTANCE',
			'backendType': 'SECOND_GEN',
			'databaseVersion': 'POSTGRES_11',
			'settings': {
				'activationPolicy': 'ALWAYS',
				'availabilityType': 'REGIONAL',
				'backupConfiguration': {
					'enabled': True,
					'startTime': '23:00'
				},
				'dataDiskSizeGb': 10,
				'dataDiskType': 'PD_SSD',
				'maintenanceWindow': {
					'day': 1,
					'hour': 23
				},
				'replicationType': 'SYNCHRONOUS',
				'storageAutoResize': True,
				'tier': 'db-custom-1-3840',
				'locationPreference': {
					'zone': 'europe-west1-b'
				}
			}
		}
	}


def generateDbUser(context):
	return {
		'type': 'sqladmin.v1beta4.user',
    	'name': context.properties[DB_NAME_KEY] + '-user',
    	'properties': {
      	'name': 'ssd_pg',
      	'host': '%',
      	'instance': context.properties[DB_NAME_KEY],
      	'password': 'password_en_clair_pas_bien'
		}
	}


def GenerateConfig(context):
	resources = [
		generateDbResource(context),
		generateDbUser(context)
	]
	return {'resources': resources}
