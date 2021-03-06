package services;

import java.util.Vector;

import domain.Prof;

public interface LocalisationService {

	public Vector<Prof> localiserVoisin(Double latitude, Double longitude, Integer perimetre);

	public double distFrom(double lat1, double lng1, double lat2, double lng2);
}