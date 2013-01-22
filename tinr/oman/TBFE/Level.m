//
//  Level.m
//  TBFE
//
//  Created by Marko Oman on 10/23/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Level.h"
#import "Retronator.TBFE.h"

@implementation Level
- (id) initWithGame:(Game *)theGame
{
	self = [super initWithGame:theGame];
	if (self != nil) {
		topShip = [[Ship alloc] init];
		enemyship = [[EnemyShip alloc] init];
		enemyship1 = [[EnemyShip1 alloc] init];
		//bullet = [[Bullet alloc] init];
		//bullet1 = [[Bullet1 alloc] init];
		
		scene = [[Scene alloc] init];
		[scene addItem:topShip];
		[scene addItem:enemyship];
		[scene addItem:enemyship1];
		
		//[scene addItem:bullet];
		//[scene addItem:bullet1];
		
	}
	return self;
}

@synthesize scene, topShip, enemyship, enemyship1;//, bullet, bullet1;

- (void) initialize{
	[self reset];
	[super initialize];
}

- (void) reset{}

- (void) dealloc
{
	[scene release];
	[super dealloc];
}


@end
