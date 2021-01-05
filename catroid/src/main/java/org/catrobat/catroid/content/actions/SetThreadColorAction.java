/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.content.actions;

import android.util.Log;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.formulaeditor.InterpretationException;

public class SetThreadColorAction extends TemporalAction {

	private Sprite sprite;
	private Formula color;

	@Override
	protected void update(float delta) {
		try {
			String colorStringInterpretation = "0xff0000";
			if (color != null) {
				colorStringInterpretation = color.interpretString(sprite);
			}

			String red = "0x" + colorStringInterpretation.substring(2, 4);
			String green = "0x" + colorStringInterpretation.substring(4, 6);
			String blue = "0x" + colorStringInterpretation.substring(6, 8);
			int redInt = Integer.decode(red);
			int greenInt = Integer.decode(green);
			int blueInt = Integer.decode(blue);

			Color colorInterpretation = new Color();
			Color.argb8888ToColor(colorInterpretation, android.graphics.Color.argb(0xFF, redInt, greenInt, blueInt));
			sprite.setEmbroideryThreadColor(colorInterpretation);
		} catch (InterpretationException interpretationException) {
			Log.d(getClass().getSimpleName(), "Formula interpretation for this specific Brick failed.", interpretationException);
		}
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setColor(Formula color) {
		this.color = color;
	}
}
