//
//  Computer.h
//  TBFE
//
//  Created by Marko Oman on 11/11/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ComputerPlayer.h"

@interface Computer : ComputerPlayer {
	
}




- (id) initWithEnemyShip:(EnemyShip *)theEnemyShip scene:(id <IScene>)theScene position:(PlayerPosition)thePosition game:(Game*)game;

@end