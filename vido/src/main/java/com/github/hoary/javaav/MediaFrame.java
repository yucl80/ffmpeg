/*
 * Copyright (C) 2013 Alex Andres
 *
 * This file is part of JavaAV.
 *
 * JavaAV is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version (subject to the "Classpath"
 * exception as provided in the LICENSE file that accompanied
 * this code).
 *
 * JavaAV is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JavaAV. If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.hoary.javaav;

public class MediaFrame {

	public enum Type { AUDIO, VIDEO }

	private long timestamp;
	private Type type;

	private boolean keyFrame;
	private boolean hasFrame;


	public MediaFrame() {

	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isKeyFrame() {
		return keyFrame;
	}

	public void setKeyFrame(boolean keyFrame) {
		this.keyFrame = keyFrame;
	}

	public Type getType() {
		return type;
	}

	public boolean hasFrame() {
		return hasFrame;
	}

}
