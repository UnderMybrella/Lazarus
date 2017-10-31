package org.abimon.lazarus

import java.awt.Polygon
import java.awt.Rectangle
import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.geom.Ellipse2D

fun Octagon(width: Int, height: Int = width): Polygon {
    val perPoint = width / 4
    val perPointY = height / 4
    return Polygon(intArrayOf(0, perPoint, perPoint * 2, width, width, perPoint * 2, perPoint, 0), intArrayOf(perPointY, 0, 0, perPointY, perPointY * 2, height, height, perPointY * 2), 8)
}

fun Circle(diameter: Int): Shape {
    val columnWidth = diameter / 4
    val width = Rectangle(diameter - 1, columnWidth - 1)
    val height = Rectangle(columnWidth - 1, diameter - 1)

    val area = Area()

    area.add(Area(width).createTransformedArea(AffineTransform.getTranslateInstance(0.0, (columnWidth + (columnWidth / 2)).toDouble())))
    area.add(Area(height).createTransformedArea(AffineTransform.getTranslateInstance((columnWidth + (columnWidth / 2)).toDouble(), 0.0)))
    //area.add(Area(Arc2D.Double(0.0, 0.0, (columnWidth + (columnWidth / 2)).toDouble(), (columnWidth + (columnWidth / 2)).toDouble(), 90.0, 90.0)))
    area.add(Area(Ellipse2D.Double(0.0, 0.0, diameter - 1.0, diameter - 1.0)))

    return area
}