/**
 * 
 */

/* switch para habilitar deshabilitar div con stule = display: xxxx */

function toggle(id) {
	/* alert("..en toggle id=" + id) */
	var element = document.getElementById(id);
	if (element.style.display == 'block') {
		/* alert("..en element.style.display == block") */
		element.style.display = 'none';
	} else {
		/* alert("..en element.style.display == else") */
		element.style.display = 'block'
	}
}
