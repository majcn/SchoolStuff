//
//  TBFELevel.m
//  TBFE
//
//  Created by Marko Oman on 10/23/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "TBFELevel.h"
#import "Retronator.TBFE.h"

@implementation TBFELevel

- (void) reset{
		topShip.position.x = 160;
		topShip.position.y = 350;
		
		enemyship.velocity.x = 0;
		enemyship.velocity.y = 1;
		enemyship.position.x = 70;
		enemyship.position.y = 50;
		
		enemyship1.velocity.x = 0;
		enemyship.velocity.y = 1;
		enemyship1.position.x =170;
		enemyship1.position.y = 50;
		
		//bullet.position.x = 150;
		//bullet.position.y = 200;
		
		//bullet1.position.x = 210; 
		//bullet1.position.y = 150;
	}
	



@end
