//
//  Player.m
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Player.h"



@implementation Player

- (id) initWithShip:(Ship *)theShip scene:(id <IScene>)theScene position:(PlayerPosition)thePosition
{
	self = [super init];
	if (self != nil) {
		ship = theShip;
		scene = theScene;
		position = thePosition;
		
		
	}
	return self;
}





- (void) updateWithGameTime:(GameTime *)gameTime {
	
}

@end

