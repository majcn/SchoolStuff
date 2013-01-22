//
//  ComputerPlayer.h
//  TBFE
//
//  Created by Marko Oman on 11/11/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Scene.h"
#import "Retronator.TBFE.classes.h"

@interface ComputerPlayer : NSObject {
	id<IScene> scene;
	PlayerPosition position;
	EnemyShip *enemyShip;
}

- (id) initWithEnemyShip:(EnemyShip *)theEnemyShip scene:(id <IScene>)theScene position:(PlayerPosition)thePosition;

- (void) updateWithGameTime:(GameTime*)gameTime;

@end

