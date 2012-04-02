/* *********************************************************************** *
 * project: org.matsim.*
 * GripsConfigDeserializer.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2012 by the members listed in the COPYING,        *
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

package org.matsim.contrib.grips.io;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.matsim.contrib.grips.config.GripsConfigModule;
import org.matsim.contrib.grips.io.jaxb.gripsconfig.GripsConfigType;
import org.matsim.core.utils.io.IOUtils;
import org.matsim.core.utils.io.MatsimJaxbXmlParser;
import org.matsim.core.utils.io.UncheckedIOException;
import org.xml.sax.SAXException;

public class GripsConfigDeserializer extends MatsimJaxbXmlParser{
	
	private static final Logger log = Logger.getLogger(GripsConfigDeserializer.class);
	

	private final GripsConfigModule gcm;

	public GripsConfigDeserializer(GripsConfigModule gcm) {
		this(GripsConfigSerializer.SCHEMA,gcm);
	}
	
	protected GripsConfigDeserializer(String schemaLocation, GripsConfigModule gcm) {
		super(schemaLocation);
		this.gcm = gcm;
	}

	@Override
	public void readFile(String filename){
		// create jaxb infrastructure
		JAXBContext jc;
		GripsConfigType gct = null;
		InputStream stream = null;
		try {
			jc = JAXBContext.newInstance(org.matsim.contrib.grips.io.jaxb.gripsconfig.ObjectFactory.class);
			Unmarshaller u = jc.createUnmarshaller();
			// validate XML file
			log.info("starting to validate " + filename);
			super.validateFile(filename, u);
			log.info("starting unmarshalling " + filename);
			stream = IOUtils.getInputStream(filename);
			JAXBElement<GripsConfigType> el = (JAXBElement<GripsConfigType>)u.unmarshal(stream);
			gct =  el.getValue(); 
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (JAXBException e) {
			throw new UncheckedIOException(e);
		} catch (SAXException e) {
			throw new UncheckedIOException(e);
		} catch (ParserConfigurationException e) {
			throw new UncheckedIOException(e);
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				log.warn("Could not close stream.", e);
			}
		}
		this.gcm.setEvacuationAreaFileName(gct.getEvacuationAreaFile().getInputFile());
		this.gcm.setNetworkFileName(gct.getNetworkFile().getInputFile());
		this.gcm.setOutputDir(gct.getOutputDir().getInputFile());
		this.gcm.setPopulationFileName(gct.getPopulationFile().getInputFile());
		this.gcm.setSampleSize(gct.getSampleSize()+"");
	}

	public static void main(String [] args) {
		log.warn("this main method exist for debugging only, meaning it will (hopefully) removed soon[gl 03/2012]");
		String input = "/Users/laemmel/tmp/gripsConfig.xml";
		GripsConfigModule gcm = new GripsConfigModule("grips");
		new GripsConfigDeserializer(gcm).readFile(input);
		
//		GripsConfigModule gcm = new GripsConfigDeserializer().deserialize(input);
		System.out.println(gcm.getOutputDir());
		new GripsConfigSerializer(gcm).write(input);
	}


}
