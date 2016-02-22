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
public class Circle {
    public Circle(Graphics g, Color color, int[] position, double radius) {
        double x = position[0];
        double y = position[1];
        g.setColor(color);
        g.drawOval((int)(x - radius), (int)(y - radius), (int)radius*2, (int)radius*2);
    }
}
