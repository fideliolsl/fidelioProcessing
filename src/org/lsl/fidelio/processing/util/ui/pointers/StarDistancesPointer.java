/*
 * Copyright 2015-2016 Jonas Drotleff for FIDELIO Project - Life Science Lab Heidelberg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @see <a href="http://life-science-lab.org">life-science-lab.org</a>
 */

package org.lsl.fidelio.processing.util.ui.pointers;

import java.awt.*;

/**
 * Created by jonas on 18.02.16.
 */
public class StarDistancesPointer {
    public StarDistancesPointer(Graphics g, Color color, String description, int[] position, int[] positions2) {
        g.setColor(color);
        int x0 = position[0];
        int y0 = position[1];
        int x1 = positions2[0];
        int y1 = positions2[1];
        g.drawLine(x0, y0, x1, y1);
    }
}
