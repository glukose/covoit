package services;

import java.util.Vector;

import domain.Prof;

public interface LocalisationService {

	public Vector<Prof> localiserVoisin(Double latitude, Double longitude, Integer perimetre);

}
