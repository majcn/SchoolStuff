//
//  IScene.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <UIKit/UIKit.h>


@protocol IScene <NSObject, NSFastEnumeration>

- (void) addItem:(id)item;
- (void) removeItem:(id)item;
- (void) clear;

@property (nonatomic, readonly) Event *itemAdded;
@property (nonatomic, readonly) Event *itemRemoved;

@end