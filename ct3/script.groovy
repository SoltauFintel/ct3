if (text.contains("jx:if") && text.contains("test")) {
	String test = getVal(text, "test")
	return "jx:if(condition=\"${test}\", lastCell=\"\")"

} else if (text.startsWith('${SUM(') && text.contains("):")) {
	String var = text.substring('${SUM('.length())
	var = var.substring(0, var.indexOf("):"))
	return '${xmap:sum("' + var + '", "g.group")}'

} else { // each
	String items = getVal(text, "items")
	if (items == null) {
		throw new RuntimeException("Parsen hat nicht geklappt! \"items\" nicht vorhanden!")
	}
	if (items.startsWith('${') && items.endsWith('}')) {
		items = items.substring(2);
		items = items.substring(0, items.length() - 1)
	}
	
	String var = getVal(text, "var")
	if (var == null) var = ""
	
	String sel = getVal(text, "select")
	if (sel != null) {
		sel = sel.replace('${', '').replace('}', '')
		sel = ", select=\"${sel}\""
	} else {
		sel = ""
	}
	return "jx:each(items=\"${items}\", var=\"${var}\"${sel}, lastCell=\"\")"
} 
		
String getVal(String a, String name) {
	int o = a.indexOf(name)
	if (o < 0) return null
	o += name.length()
	int oo = a.indexOf("=", o)
	if (oo < 0) return null
	o = oo + 1
	oo = a.indexOf("\"", o)
	if (oo < 0) return null
	int start = oo + 1
	oo = a.indexOf("\"", start)
	if (oo < 0) return null
	return a.substring(start, oo)
}
