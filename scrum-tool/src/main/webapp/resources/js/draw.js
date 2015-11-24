function getRandomColor() {
	var letters = '0123456789ABCDEF'.split('');
	var color = '#';
	for (var i = 0; i < 6; i++ ) {
		color += letters[Math.floor(Math.random() * 16)];
	}
	return color;
}

function draw(container, data){
	var context = document.getElementById(container).getContext('2d');
	var skillsChart = new Chart(context).Pie(data);
}