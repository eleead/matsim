/* *********************************************************************** *
 * project: org.matsim.*
 * PlansAnalyzer.java
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

package playground.anhorni.locationchoice.analysis;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.matsim.gbl.Gbl;
import org.matsim.plans.Act;
import org.matsim.plans.MatsimPlansReader;
import org.matsim.plans.Person;
import org.matsim.plans.Plan;
import org.matsim.plans.Plans;
import org.matsim.plans.PlansReaderI;
import org.matsim.utils.io.IOUtils;
import org.matsim.utils.misc.Counter;


/**
 * @author anhorni
 */

public class PlansAnalyzer {

	private Plans plans=null;

	private final static Logger log = Logger.getLogger(PlansAnalyzer.class);


	/**
	 * @param
	 *  - path of the plans file
	 */
	public static void main(String[] args) {

		if (args.length < 1 || args.length > 1 ) {
			System.out.println("Too few or too many arguments. Exit");
			System.exit(1);
		}
		String plansfilePath = args[0];
		String type[] = {"s", "l"};

		log.info(plansfilePath);

		PlansAnalyzer analyzer = new PlansAnalyzer();
		analyzer.init(plansfilePath);
		
		for (int i=0; i<2; i++) {		
			analyzer.analyze(type[i]);
		}	
	}

	private void init(final String plansfilePath) {


		this.plans=new Plans(false);
		final PlansReaderI plansReader = new MatsimPlansReader(this.plans);
		plansReader.readFile(plansfilePath);
		log.info("plans reading done");
	}

	
	private void analyze(String type) {
		
		try {
			final String header="Person_id\tActDuration";
			final BufferedWriter out = IOUtils.getBufferedWriter("./output/plananalysis"+type+".txt");
			out.write(header);
			out.newLine();
			
			Iterator<Person> person_iter = this.plans.getPersons().values().iterator();
			Counter counter = new Counter(" person # ");
			while (person_iter.hasNext()) {
				Person person = person_iter.next();
				counter.incCounter();
	
				Plan selectedPlan = person.getSelectedPlan();
	
				final ArrayList<?> actslegs = selectedPlan.getActsLegs();
				for (int j = 0; j < actslegs.size(); j=j+2) {
					final Act act = (Act)actslegs.get(j);	
					if (act.getType().startsWith(type)) {
						out.write(person.getId().toString()+"\t"+
								String.valueOf(act.getDur()));
						out.newLine();
					}
				}
				out.flush();
			}			
			out.close();
			}
			catch (final IOException e) {
				Gbl.errorMsg(e);
			}
		}
}

