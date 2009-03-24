/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package org.matsim.network;

import java.util.HashMap;
import java.util.Map;

import org.matsim.interfaces.basic.v01.Coord;
import org.matsim.interfaces.basic.v01.Id;
import org.matsim.interfaces.basic.v01.population.BasicLeg;
import org.matsim.interfaces.core.v01.Link;
import org.matsim.interfaces.core.v01.Node;
import org.matsim.interfaces.core.v01.Route;
import org.matsim.population.routes.NodeCarRouteFactory;
import org.matsim.population.routes.RouteFactory;

/**
 * @author dgrether
 */
public class NetworkFactory {

	private LinkFactory linkFactory = null;

	private final Map<BasicLeg.Mode, RouteFactory> routeFactories = new HashMap<BasicLeg.Mode, RouteFactory>();

	public NetworkFactory() {
		this.linkFactory = new LinkFactoryImpl();
		this.routeFactories.put(BasicLeg.Mode.car, new NodeCarRouteFactory());
	}

	public Node createNode(final Id id, final Coord coord, final String type) {
		return new NodeImpl(id, coord, type);
	}

	public Link createLink(final Id id, final Node from, final Node to,
			final NetworkLayer network, final double length, final double freespeedTT, final double capacity,
			final double lanes) {
		return this.linkFactory.createLink(id, from, to, network, length, freespeedTT, capacity, lanes);
	}

	/**
	 * @param mode the transport mode the route should be for
	 * @param startLink the link where the route starts
	 * @param endLink the link where the route ends
	 * @return a new Route for the specified mode
	 * @throws IllegalArgumentException if no {@link RouteFactory} is registered that creates routes for the specified mode.
	 *
	 * @see #setRouteFactory(org.matsim.interfaces.basic.v01.population.BasicLeg.Mode, RouteFactory)
	 */
	public Route createRoute(final BasicLeg.Mode mode, final Link startLink, final Link endLink) {
		final RouteFactory factory = this.routeFactories.get(mode);
		if (factory == null) {
			throw new IllegalArgumentException("There is no factory known to create routes for leg-mode " + mode.toString());
		}
		return factory.createRoute(startLink, endLink);
	}

	@Deprecated
	public Route createRoute(final BasicLeg.Mode mode) {
		final RouteFactory factory = this.routeFactories.get(mode);
		if (factory == null) {
			throw new IllegalArgumentException("There is no factory known to create routes for leg-mode " + mode.toString());
		}
		return factory.createRoute(null, null);
	}

	/**
	 * Registers a {@link RouteFactory} for the specified mode. If <code>factory</code> is <code>null</code>,
	 * the existing entry for this <code>mode</code> will be deleted.
	 *
	 * @param mode
	 * @param factory
	 */
	public void setRouteFactory(final BasicLeg.Mode mode, final RouteFactory factory) {
		if (factory == null) {
			this.routeFactories.remove(mode);
		} else {
			this.routeFactories.put(mode, factory);
		}
	}
	
	public void setLinkFactory(final LinkFactory factory) {
		this.linkFactory = factory;
	}

	public boolean isTimeVariant() {
		return (this.linkFactory instanceof TimeVariantLinkFactory);
	}

}
