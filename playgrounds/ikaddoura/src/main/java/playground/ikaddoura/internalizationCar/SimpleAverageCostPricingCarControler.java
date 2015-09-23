/* *********************************************************************** *
 * project: org.matsim.*
 * TestControler.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2011 by the members listed in the COPYING,        *
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

/**
 * 
 */
package playground.ikaddoura.internalizationCar;


import java.io.IOException;
import org.apache.log4j.Logger;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioImpl;
import org.matsim.vis.otfvis.OTFFileWriterFactory;

/**
 * @author ikaddoura
 *
 */
public class SimpleAverageCostPricingCarControler {
	
	private static final Logger log = Logger.getLogger(SimpleAverageCostPricingCarControler.class);
	
	static String configFile;
	static boolean acp;
			
	public static void main(String[] args) throws IOException {
				
		if (args.length > 0) {
			
			configFile = args[0];		
			log.info("first argument (config file): "+ configFile);
			
			acp = Boolean.parseBoolean(args[1]);
			log.info("second argument (enabling average cost pricing: true/false): "+ acp);

		} else {
			configFile = "../../runs-svn/internalizationCar/input/config_internalizationCar.xml";
			acp = true;
		}
		
		SimpleAverageCostPricingCarControler main = new SimpleAverageCostPricingCarControler();
		main.run();
	}
	
	private void run() {
		
		Controler controler = new Controler(configFile);

		if (acp) {
			
			log.info("Internalization of congestion effects is enabled.");
			TollHandler tollHandler = new TollHandler(controler.getScenario());
			TollDisutilityCalculatorFactory tollDisutilityCalculatorFactory = new TollDisutilityCalculatorFactory(tollHandler);
			controler.setTravelDisutilityFactory(tollDisutilityCalculatorFactory);
			controler.addControlerListener(new AverageCostPricing( (ScenarioImpl) controler.getScenario(), tollHandler ));
		
		} else {
			log.info("Internalization of congestion effects is disabled.");
		}
		
		controler.addControlerListener(new WelfareAnalysisControlerListener((ScenarioImpl) controler.getScenario()));
		controler.addSnapshotWriterFactory("otfvis", new OTFFileWriterFactory());	
		controler.setOverwriteFiles(true);
		controler.run();
		
	}
}
	