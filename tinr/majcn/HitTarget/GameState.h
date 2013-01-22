//
//  GameState.h
//  friHockey
//
//  Created by Matej Jan on 22.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "majcn.HitTarget.classes.h"

@interface GameState : GameComponent {
	HitTarget *hitTarget;
}

- (void) activate;
- (void) deactivate;

@end
