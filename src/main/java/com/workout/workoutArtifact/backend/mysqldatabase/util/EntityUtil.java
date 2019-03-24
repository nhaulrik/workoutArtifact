package com.workout.workoutArtifact.backend.mysqldatabase.util;

import org.dom4j.tree.AbstractEntity;

public final class EntityUtil {

	public static final String getName(Class<? extends AbstractEntity> type) {
		// All main entities have simple one word names, so this is sufficient. Metadata
		// could be added to the class if necessary.
		return type.getSimpleName();
	}
}
