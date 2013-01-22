//
//  GameProgress.h
//  friHockey
//
//  Created by Matej Jan on 22.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "majcn.HitTarget.classes.h"

@interface GameProgress : NSObject <NSCoding> {
	int highScore;
}

+ (GameProgress *) loadProgress;
+ (void) deleteProgress;
- (void) saveProgress;
- (void) incScore;
- (int) getScore;

@end
