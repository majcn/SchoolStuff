//
//  IConvexCollider.h
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@protocol IConvexCollider

@property (nonatomic, readonly) ConvexPolygon *bounds;

@end

