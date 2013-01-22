//
//  Level.h
//  TBFE
//
//  Created by Marko Oman on 10/23/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Retronator.TBFE.classes.h"

@interface Level : GameComponent {
	Scene *scene;
	Ship *topShip;
	EnemyShip *enemyship;
	EnemyShip1 *enemyship1;
	//Bullet *bullet;
	//Bullet1 *bullet1;
	
}

@property(nonatomic, readonly) id<IScene> scene;
@property(nonatomic, readonly) Ship *topShip;
@property(nonatomic, readonly) EnemyShip *enemyship;
@property(nonatomic, readonly) EnemyShip1 *enemyship1;
//@property(nonatomic, readonly) Bullet *bullet;
//@property(nonatomic, readonly) Bullet1 *bullet1;

-(void) reset;
@end
