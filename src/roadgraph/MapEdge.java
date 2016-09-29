/**
 * 
 */
package roadgraph;

import java.util.LinkedList;
import java.util.List;

import geography.GeographicPoint;

/**
 * @author UCSD Intermediate Programming MOOC team
 *
 *         A directed edge in a map graph from Node start to Node end
 */
class MapEdge {
	/** The name of the road */
	private String roadName;

	/** The type of the road */
	private String roadType;

	/** The two endpoints of the edge */
	private MapNode start;
	private MapNode end;

	private int speedLimit;

	/** The length of the road segment, in km */
	private double length;

	static final double DEFAULT_LENGTH = 0.01;

	/**
	 * Create a new MapEdge object
	 * 
	 * @param roadName
	 * @param n1
	 *            The point at one end of the segment
	 * @param n2
	 *            The point at the other end of the segment
	 * 
	 */
	MapEdge(String roadName, MapNode n1, MapNode n2) {
		this(roadName, "", n1, n2, DEFAULT_LENGTH);
	}

	MapEdge(String roadName, String roadType, MapNode n1, MapNode n2) {
		this(roadName, roadType, n1, n2, DEFAULT_LENGTH);
	}

	MapEdge(String roadName, String roadType, MapNode n1, MapNode n2, double length) {
		this.roadName = roadName;
		start = n1;
		end = n2;
		this.roadType = roadType;
		this.length = length;
	}

	// return the MapNode for the end point
	MapNode getEndNode() {
		return end;
	}

	// return the location of the start point
	GeographicPoint getStartPoint() {
		return start.getLocation();
	}

	// return the location of the end point
	GeographicPoint getEndPoint() {
		return end.getLocation();
	}

	// return the length
	double getLength() {
		return length;
	}

	// return road name
	public String getRoadName() {
		return roadName;
	}

	// return road name
	public String getRoadType() {
		return roadType;
	}

	public int getSpeedLimit() {

		/*
		 * 
		 * residential 30 living_street 30 secondary 60 unclassified 30 tertiary
		 * 30 primary_link 80 primary 80 motorway 60 secondary_link 60
		 * motorway_link 60 tertiary_link 30
		 * 
		 * 
		 */

		String roadType = this.getRoadType();

		if (roadType.equals("residential") || roadType.equals("living_street") || roadType.equals("unclassified")
				|| roadType.equals("tertiary") || roadType.equals("tertiary_link")) {

			return 30;
		} else if (roadType.equals("secondary") || roadType.equals("motorway") || roadType.equals("secondary_link")
				|| roadType.equals("motorway_link")) {

			return 60;
		} else {

			return 80;
		}

	}

	// given one node in an edge, return the other node
	MapNode getOtherNode(MapNode node) {
		if (node.equals(start))
			return end;
		else if (node.equals(end))
			return start;
		throw new IllegalArgumentException("Looking for " + "a point that is not in the edge");
	}

	// return String containing details about the edge
	public String toString() {
		String toReturn = "[EDGE between ";
		toReturn += "\n\t" + start.getLocation();
		toReturn += "\n\t" + end.getLocation();
		toReturn += "\nRoad name: " + roadName + " Road type: " + roadType + " Segment length: "
				+ String.format("%.3g", length) + "km";

		return toReturn;
	}

}
