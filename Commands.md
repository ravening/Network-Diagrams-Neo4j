# Common neo4j commands to query the database

## Get all vlans configured on interface eth40
MATCH p=(i:Interfaces)-[r:VLAN]-() where i.name="eth40" RETURN p LIMIT 100

## Get all interfaces where vlan 10 is consfigured
MATCH p=(v:Vlan)-[r:VLAN]-() where v.vlanId=10 RETURN p LIMIT 100

## Get all the interfaces configured on arista equipment
MATCH p=(e:Equipment)-[r:INTERFACES]->() where e.Equipment="arista"  RETURN p LIMIT 100

## Get connection details between any two devices
MATCH p=(s:Equipment)-[r:CONNECTED_TO]-(d:Equipment) where s.name="node99" or d.name="node01"  RETURN p LIMIT 25
