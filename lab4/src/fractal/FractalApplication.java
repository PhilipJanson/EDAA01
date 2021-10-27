package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {

	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[3];
		fractals[0] = new Koch(500);
		fractals[1] = new Mountain("Mountain Fractal", new Point(150, 600), new Point(380, 100), new Point(890, 500));
		fractals[2] = new Mountain("Mountain Fractal (Big)", new Point(150, 900), new Point(700, 100), new Point(1750, 820));

		new FractalView(fractals, "Fraktaler", 1000, 800);
	}
}
