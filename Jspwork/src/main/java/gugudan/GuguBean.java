package gugudan;

public class GuguBean {
	//구구단 계산 각 값을 배열에 저장
	public int[] times(int dan) {
		int[] gugu = new int[19];
		
		for(int i=1; i<20; i++) {
			gugu[i-1] = dan * i;
		}
		return gugu;
	}
}
