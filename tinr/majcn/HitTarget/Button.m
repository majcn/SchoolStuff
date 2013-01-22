//
//  Button.m
//  friHockey
//
//  Created by Matej Jan on 21.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import "Button.h"
#import "majcn.HitTarget.h"
#import "MirageEnums.h"
#import "Retronator.Xni.Framework.Graphics.h"
#import "Retronator.Xni.Framework.Input.Touch.h"

@implementation Button

- (id) initWithInputArea:(Rectangle*)theInputArea
			 background:(Texture2D*)background
				   font:(SpriteFont*)font 
				   text:(NSString*)text
{
	self = [super init];
	if (self != nil) {
		
		inputArea = [theInputArea retain];
		enabled = YES;
				
		backgroundImage = [[Image alloc] initWithTexture:background position:[Vector2 vectorWithX:inputArea.x y:inputArea.y]];
		label = [[Label alloc] initWithFont:font 
									   text:text 
								   position:[Vector2 vectorWithX:inputArea.x + 10 y:inputArea.y + inputArea.height/2]];		
		label.verticalAlign = VerticalAlignMiddle;
		
		self.backgroundColor = [Color white];
		self.backgroundHoverColor = [Color dimGray];
		
		self.labelColor = [Color red];
		self.labelHoverColor = [Color blue];
		
	}
	return self;
}

@synthesize inputArea, enabled, isDown, wasPressed, wasReleased, scene, backgroundImage, label;
@synthesize labelColor, labelHoverColor, backgroundColor, backgroundHoverColor;

- (void) setLabelColor:(Color *)value {
	[value retain];
	[labelColor release];
	labelColor = value;
	label.color = labelColor;
}

- (void) setBackgroundColor:(Color *)value {
	[value retain];
	[backgroundColor release];
	backgroundColor = value;
	backgroundImage.color = backgroundColor;
}

- (void) addedToScene:(id <IScene>)theScene {
	// Add child items to scene.
	[theScene addItem:backgroundImage];
	[theScene addItem:label];
}

- (void) removedFromScene:(id <IScene>)theScene {
	// Remove child items.
	[theScene removeItem:backgroundImage];
	[theScene removeItem:label];
}

- (void) update {	
	if (!enabled) {
		return;
	}
		
	TouchCollection *touches = [TouchPanelHelper getState];
	if (!touches) {
		return;
	}
		
	BOOL wasDown = isDown;
	
	isDown = NO;
	wasPressed = NO;
	wasReleased = NO;
	
	for (TouchLocation *touch in touches) {
		if ([inputArea containsX:touch.position.x y:touch.position.y] && touch.state != TouchLocationStateInvalid) {
			if (touch.state == TouchLocationStatePressed) {
				pressedID = touch.identifier;
				wasPressed = YES;
			}
			
			// Only act to the touch that started the push.
			if (touch.identifier == pressedID) {
				if (touch.state == TouchLocationStateReleased) {
					wasReleased = YES;
				} else {
					isDown = YES;		
				}
			}
		}
	}
		
	if (isDown && !wasDown) {
		backgroundImage.color = backgroundHoverColor;
		label.color = labelHoverColor;
	} else if (!isDown && wasDown) {
		backgroundImage.color = backgroundColor;
		label.color = labelColor;
	}
}

- (void) dealloc
{
	[backgroundImage release];
	[label release];
	[inputArea release];
	[super dealloc];
}

@end
