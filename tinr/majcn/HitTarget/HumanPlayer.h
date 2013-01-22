//
//  HumanPlayer.h
//  HitTarget
//
//  Created by Gregor Majcen on 12/7/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"
#import "Retronator.Xni.Framework.Audio.h"

#import "Player.h"

@interface HumanPlayer : Player {
	Vector2 *pressedPosition;
	SoundEffect *sound;
}

@end
