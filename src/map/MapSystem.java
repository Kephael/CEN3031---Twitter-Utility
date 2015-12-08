package map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class MapSystem extends PApplet {
	private static final long serialVersionUID = 7018868149918876490L;
	UnfoldingMap map;

	public void setup() {
		size(775, 270);
		map = new UnfoldingMap(this, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
	}

	public void draw() {
		for (int i = 0 ; i < util.TwitterParser.location.size(); i++) { // get locations from the location ArrayList
			Location newLocation = new Location(util.TwitterParser.location.get(i).getLatitude(), util.TwitterParser.location.get(i).getLongitude()); // location gets latitude and longitude
			SimplePointMarker newMarker = new SimplePointMarker(newLocation); // new marker at location of user with geolocation data
			map.addMarker(newMarker); // marker added to map
		}
		map.draw();
	}
}
