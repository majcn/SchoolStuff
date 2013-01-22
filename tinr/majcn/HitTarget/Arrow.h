//
//  Arrow.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import "IVelocity.h"
#import "IRotationAngle.h"
#import "IPosition.h"

@interface Arrow : NSObject <IPosition, IRotationAngle, IVelocity>
{
	Vector2 *position;
	Vector2 *velocity;
	float rotationAngle;
}

@end
