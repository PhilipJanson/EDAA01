package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

	private HashMap<Side, Point> map;

	private String name;

	private Point a;
	private Point b;
	private Point c;

	private int random;

	public Mountain(String n, Point p1, Point p2, Point p3) {
		super();
		map = new HashMap<Side, Point>();
		name = n;
		a = p1;
		b = p2;
		c = p3;
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, a, b, c, 20);
	}

	private void fractalLine(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3, double dev) {
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		} else {

			random = (int) RandomUtilities.randFunc(dev);
			Point p12 = getMiddle(p1, p2);
			Point p23 = getMiddle(p2, p3);
			Point p13 = getMiddle(p1, p3);
			fractalLine(turtle, order - 1, p1, p12, p13, dev / 2);
			fractalLine(turtle, order - 1, p12, p2, p23, dev / 2);
			fractalLine(turtle, order - 1, p13, p23, p3, dev / 2);
			fractalLine(turtle, order - 1, p23, p13, p12, dev / 2);
		}
	}

	private Point getMiddle(Point p1, Point p2) {
		Side side = new Side(p1, p2);

		for (Side search : map.keySet()) {
			if (search.equals(side)) {
				Point p = map.get(search);
				map.remove(search);
				return p;
			}
		}
		
		Point p = new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2 + random);
		map.put(side, p);
		
		return p;
	}
}
