function play() {
	let name = [
		"강정현", "권지혜", "김민정", "김은효", "노승우", "성의석", "안재훈", "오화룡", "유성길"
		, "유세현", "윤기은", "이가은", "이경철", "이유진", "이진성", "조은별", "한주훈"
	];
	let numbers = new Array(5); // 랜덤 번호 5개 저장
	for (let i = 0; i < numbers.length; i++) {
		let num = Math.floor(Math.random() * name.length);
		numbers[i] = num;

		for (let j = 0; j < i; j++) {
			if (numbers[j] == numbers[i]) {
				//console.log("중복 번호 : " + numbers[i]);
				i--;
			}
		}
	}

	let winners = new Array(5);
	//출력
	for (let i = 0; i < winners.length; i++) {
		winners[i] = name[numbers[i]];
	}

	document
		.getElementById("display")
		.innerHTML = winners;
}