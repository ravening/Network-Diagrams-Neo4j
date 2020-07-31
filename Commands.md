# Common neo4j commands to query the database

## Get connection details between any two devices
MATCH p=(s:Equipment)-[r:CONNECTED_TO]-(d:Equipment) where s.name="node99" or d.name="node01"  RETURN p LIMIT 100

MATCH p=(s:Equipment)-[:PORT|:CONNECTED_TO]-() where s.name="node99" or s.name="netapp99" RETURN p LIMIT 100

MATCH p=()-[r:PORT]->() RETURN p LIMIT 100

## Get all the interfaces configured on arista equipment
MATCH p=(e:Equipment)-[r:PORT]->() where e.name="arista"  RETURN p LIMIT 100

## Get all vlans configured on interface eth40
MATCH p=(i:Interfaces)-[r:VLAN]-() where i.equipment="switch1" and i.name="xe-0/0/1" RETURN p LIMIT 100

## Get all interfaces where vlan 10 is consfigured
MATCH p=(v:Vlan)-[r:VLAN]-() where v.vlanId=10 RETURN p LIMIT 100
MATCH p=(v:Vlan)-[r:VLAN]->() where v.vlanId=1 RETURN p LIMIT 100


## Delete all nodes
MATCH (n) DETACH DELETE n
