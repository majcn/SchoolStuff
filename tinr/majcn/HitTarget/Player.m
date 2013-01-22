//
//  Player.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Player.h"
#import	"majcn.HitTarget.h"

@implementation Player

- (id) initWithGame:(Game*)theGame Archer:(Archer*)theArcher Arrow:(Arrow*)theArrow;
{	
	self = [super initWithGame:theGame];
	if (self != nil)
	{
		archer = theArcher;
		arrow = theArrow;

	}
	return self;
}

- (void) dealloc
{
	[super dealloc];
}


@end
