//
//  AnimatedSprite.h
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Retronator.TBFE.classes.h"

@interface AnimatedSprite : NSObject {
	NSMutableArray *frames;
	NSTimeInterval duration;
	BOOL looping;
}

- (id) initWithDuration:(NSTimeInterval)theDuration;

@property (nonatomic) NSTimeInterval duration;
@property (nonatomic) BOOL looping;

- (void) addFrame:(AnimatedSpriteFrame*)frame;

- (Sprite*) spriteAtTime:(NSTimeInterval)time;

@end

