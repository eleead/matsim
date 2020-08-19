package org.matsim.contrib.dvrp.passenger;

import static org.matsim.contrib.dvrp.passenger.PassengerEngineQSimModule.PassengerEngineType.DEFAULT;

import org.matsim.contrib.dvrp.run.AbstractDvrpModeQSimModule;

public class PassengerEngineQSimModule extends AbstractDvrpModeQSimModule {
	public enum PassengerEngineType {
		DEFAULT, WITH_PREBOOKING
	}

	private final PassengerEngineType type;

	public PassengerEngineQSimModule(String mode) {
		this(mode, DEFAULT);
	}

	public PassengerEngineQSimModule(String mode, PassengerEngineType type) {
		super(mode);
		this.type = type;
	}

	@Override
	protected void configureQSim() {
		switch (type) {
			case DEFAULT:
				addModalComponent(PassengerEngine.class, DefaultPassengerEngine.createProvider(getMode()));
				return;
			case WITH_PREBOOKING:
				addModalComponent(PassengerEngine.class, PassengerEngineWithPrebooking.createProvider(getMode()));
				return;
			default:
				throw new IllegalStateException("Type: " + type + " is not supported");
		}
	}
}
