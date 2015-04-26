//
//  SecondViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/1/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CarScrollViewController.h"
#import <AudioToolbox/AudioToolbox.h>
#import <AVFoundation/AVFoundation.h>

@interface SecondViewController : UIViewController <AVAudioPlayerDelegate>
//@property (weak, nonatomic) IBOutlet UIWebView *firstWebView;
@property NSString *userInput;
@property (weak, nonatomic) IBOutlet UIButton *thisButton;
@property (weak, nonatomic) IBOutlet UITextField *inputCode;
@property NSString *code;
@property (weak, nonatomic) IBOutlet UIButton *sameCodeButton;
@property (weak, nonatomic) NSString *soundPath;
@property (nonatomic) AVAudioPlayer * audio;

- (IBAction)codeButton:(id)sender;



- (IBAction)findButton:(id)sender;

@end

