#################################################################
#	Developer Team						#
# 	Zdravko Danailov		Krassen Deltchev	#
#	nqkoi_ot_bg@yahoo.com		Krassen.Deltchev@rub.de #
#################################################################

Installation notes on the XAdES.jar to the Apache XMLSec Project:
-----------------------------------------------------------------

1. Firstly copy the java-classes:
	- ElementProxy.java 
	- XMLUtils.java
 and replace the original ones in( absolute path in the Apache 
 XMLSecproject's package):
	' \xml-security-1_3_0\src\org\apache\xml\security\utils'
2. use the Eclipse IDE and import the XAdES.jar project's library 
 using the :
	project's 'Properties'-menu-> Java-Buildpath->Libraries->
	->'Add External Jars...'
3. Enjoy!
