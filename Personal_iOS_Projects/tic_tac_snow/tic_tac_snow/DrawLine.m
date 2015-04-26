//
//  DrawLine.m
//  tic_tac_snow
//
//  Created by Hao Zhou on 2/9/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "DrawLine.h"

@implementation DrawLine

- (void)drawRect:(CGRect)rect {
    
    CGContextRef context = UIGraphicsGetCurrentContext();
    // set the stroke color and width
    CGContextSetRGBStrokeColor(context, 0.0, 255.0, 0.0, 1.0);
    CGContextSetLineWidth(context, 8.0);
    // add a line to your second point
    CGContextMoveToPoint(context, self.firstPoint.x, self.firstPoint.y);
    CGContextAddLineToPoint(context, self.secondPoint.x, self.secondPoint.y);
    // tell the context to draw the stroked line
    CGContextStrokePath(context);
}

@end
