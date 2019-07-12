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


def CreateCloudSQLInstance(basename, apiDep, zone, minSalt=None, maxSalt=None):
    import random
    random.seed()
    minRand = 0 if minSalt is None else minSalt
    maxRand = 100 if maxSalt is None else maxSalt
    salt = random.randint(minRand, maxRand)
    instance_name = (basename + '-{}').format(salt)
    return (instance_name, {
        'name': instance_name,
        'type': 'sqladmin.v1beta4.instance',
        'metadata': generateDependencies([apiDep]),
        'properties': {
            'backendType': 'SECOND_GEN',
            'databaseVersion': 'POSTGRES_11',
            'region': zone[0:zone.rfind('-')],
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


def CreateDatabaseInInstance(name, instance_name, db_name):
    return {
        'name': name,
        'type': 'sqladmin.v1beta4.database',
        'metadata': generateDependencies([instance_name]),
        'properties': {
            'instance': instance_name,
            'name': db_name
        }
    }


def CreateUser(instance_name, db_name, username, password, deps):
    return {
        'name': username,
        'type': 'sqladmin.v1beta4.user',
        'metadata': generateDependencies(deps),
        'properties': {
            'instance': instance_name,
            'host': db_name,
            'password': password
        }
    }


def GenerateConfig(context):
    enable_api_resource_name = 'enable-api'
    project_id = context.env['project']
    min_db_idx = context.properties['minDbIndex']
    max_db_idx = context.properties['maxDbIndex']
    instance, instance_resource = CreateCloudSQLInstance(
        'gcpmamie-prod',
        enable_api_resource_name,
        context.properties['zone'],
        minSalt=min_db_idx,
        maxSalt=max_db_idx
    )
    dbname = context.properties['dbName']
    user = context.properties['username']
    password = context.properties['password']
    return {
        'resources': [
            EnableAPIs(enable_api_resource_name, project_id),
            instance_resource,
            CreateDatabaseInInstance('database-creation', instance, dbname),
            CreateUser(instance, dbname, user, password, ['database-creation'])
        ]
    }
