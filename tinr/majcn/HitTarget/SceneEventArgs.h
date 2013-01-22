//
//  SceneEventArgs.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "EventArgs.h"

@interface SceneEventArgs : EventArgs {
	id item;
}

- (id) initWithItem:(id)theItem;
+ (SceneEventArgs*) eventArgsWithItem:(id)theItem;

@property (nonatomic, readonly) id item;

@end
