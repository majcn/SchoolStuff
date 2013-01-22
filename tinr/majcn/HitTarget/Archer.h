//
//  Archer.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import "IRotationAngle.h"
#import "IPosition.h"

@interface Archer : NSObject <IPosition, IRotationAngle>
{
	Vector2 *position;
	float rotationAngle;
	Arrow *arrow;
	Fire *fire;
	
	BOOL launched;
	float power;
}

@property (nonatomic) BOOL launched;
@property (nonatomic, retain) Arrow *arrow;
@property (nonatomic, retain) Fire *fire;
@property (nonatomic) float power;

@end
