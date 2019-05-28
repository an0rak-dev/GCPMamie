def GenerateConfig(context):
	resources = [{
		'type': 'sqladmin.v1beta4.instance',
    	'name': context.properties['db-instance-name'],
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
	}]
	return {'resources': resources}
