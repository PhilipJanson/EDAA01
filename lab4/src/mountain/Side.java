package mountain;

public class Side {

	private Point a;
	private Point b;

	public Side(Point p1, Point p2) {
		a = p1;
		b = p2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Side) {
			Side side = (Side) obj;
			return side.a.equals(a) && side.b.equals(b) || side.b.equals(a) && side.a.equals(b);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}
}
