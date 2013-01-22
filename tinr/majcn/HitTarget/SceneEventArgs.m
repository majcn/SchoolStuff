//
//  SceneEventArgs.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "SceneEventArgs.h"


@implementation SceneEventArgs

- (id) initWithItem:(id)theItem;
{
	self = [super init];
	if (self != nil) {
		item = theItem;
	}
	return self;
}

+ (SceneEventArgs*) eventArgsWithItem:(id)theItem {
	return [[[SceneEventArgs alloc] initWithItem:theItem] autorelease];
}

@synthesize item;

@end
