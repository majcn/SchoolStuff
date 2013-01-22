//
//  DemoPlayer.h
//  HitTarget
//
//  Created by Gregor Majcen on 12/7/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import "Player.h"

@interface DemoPlayer : Player {
	float powerInc;
	float maxPower;
	float angleInc;
	float maxAngle;
}

@end
