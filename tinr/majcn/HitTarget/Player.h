//
//  Player.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import	"Retronator.Xni.Framework.Input.Touch.h"

@interface Player : GameComponent
{
	Archer *archer;
	Arrow *arrow;
}

- (id) initWithGame:(Game*)theGame Archer:(Archer*)theArcher Arrow:(Arrow*)theArrow;

@end
