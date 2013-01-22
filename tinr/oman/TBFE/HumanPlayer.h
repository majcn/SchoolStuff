//
//  HumanPlayer.h
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Player.h"

@interface HumanPlayer : Player {
	Rectangle *inputArea;
	BOOL grabbed;
	Vector2 *touchOffset;
}

- (id) initWithShip:(Ship *)theShip scene:(id <IScene>)theScene position:(PlayerPosition)thePosition game:(Game*)game;

@end

