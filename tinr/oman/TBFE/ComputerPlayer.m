//
//  ComputerPlayer.m
//  TBFE
//
//  Created by Marko Oman on 11/11/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "ComputerPlayer.h"


@implementation ComputerPlayer
- (id) initWithEnemyShip:(EnemyShip *)theEnemyShip scene:(id <IScene>)theScene position:(PlayerPosition)thePosition
{
	self = [super init];
	if (self != nil) {
		enemyShip = theEnemyShip;
		scene = theScene;
		position = thePosition;
		
	}
	return self;
}

@end
