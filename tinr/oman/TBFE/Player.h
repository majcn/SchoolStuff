//
//  Player.h
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Scene.h"
#import "Retronator.TBFE.classes.h"

@interface Player : NSObject {
	Ship *ship;
	id<IScene> scene;
	PlayerPosition position;
	
}

- (id) initWithShip:(Ship*)theShip scene:(id<IScene>)theScene position:(PlayerPosition)thePosition;


- (void) updateWithGameTime:(GameTime*)gameTime;
	
@end
