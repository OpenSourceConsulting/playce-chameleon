
/* IP check Validation */
function ipValidate(value) {
	var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
	var ipArray = value.match(ipPattern);
	if (ipArray == null) {
		return false;
	} else {
		for (i = 1; i < 5; i++) {
			thisSegment = ipArray[i];
			if (thisSegment > 255) {
				return false;
			}
			if ((i == 0) && (thisSegment > 255)) {
				return false;
			}
		}
	}
	return true;
}

/* Port check Validation */
function portValidate(value) {
	var portPattern = /^\d+$/;
	if(value.match(portPattern) == null) {
		return false;
	} else {
		if(value > 65535)
			return false;
	}
	return true;
}

/* Digit check Validation */
function digitValidate(value) {
	var pattern = /^\d+$/;
	if(value.match(pattern) == null) {
		return false;
	} else {
		return true;
	}
}
